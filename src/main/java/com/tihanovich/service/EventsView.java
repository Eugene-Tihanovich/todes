package com.tihanovich.service;

import com.tihanovich.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named("treeEventsView")
@ViewScoped
@Getter
@Setter
public class EventsView implements Serializable {

    private TreeNode root;
    private TreeNode selectedNode;
    private List<Tab> tabs = new ArrayList<>();

    @Inject
    private ModelService modelService;

    @PostConstruct
    public void init() {
        root = modelService.createDocuments();
    }

    public void onNodeSelect(NodeSelectEvent event) {
        Model model = (Model) event.getTreeNode().getData();
        if (model.getContent() != null) {
            Tab tab = new Tab();
            tab.setId(model.getName());
            tab.setTitle(model.getContent());
            tab.setTitleStyle(String.valueOf(UUID.randomUUID()));
            tabs.add(tab);
        }
    }

    public void onTabClose(TabCloseEvent event) {
        for (int i = tabs.size() - 1; i >= 0; i--) {
            if (tabs.get(i).getId().equals(event.getTab().getTitle())) {
                tabs.remove(i);
            }
        }
    }
}
