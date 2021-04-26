package com.tihanovich.service;

import com.tihanovich.model.Model;
import com.tihanovich.parsing.Parse;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class ModelService implements Serializable {

    private final Parse parse = new Parse();
    private final Map<Integer, Model> modelMap = parse.getModels(10);

    public ModelService() throws IOException, InterruptedException {
    }

    public TreeNode createDocuments() {
        TreeNode root = new DefaultTreeNode(new Model(), null);

        TreeNode rootNode = new DefaultTreeNode(modelMap.get(1), root);

        TreeNode node1 = new DefaultTreeNode(modelMap.get(2), rootNode);
        TreeNode node2 = new DefaultTreeNode(modelMap.get(3), rootNode);
        TreeNode node3 = new DefaultTreeNode(modelMap.get(4), node1);
        TreeNode node4 = new DefaultTreeNode(modelMap.get(5), node1);
        TreeNode node5 = new DefaultTreeNode("content", modelMap.get(9), node1);
        TreeNode node6 = new DefaultTreeNode("content", modelMap.get(6), node3);
        TreeNode node7 = new DefaultTreeNode("content", modelMap.get(7), node3);
        TreeNode node8 = new DefaultTreeNode("content", modelMap.get(8), node2);

        return root;
    }
}
