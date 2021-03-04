package com.example.demo.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    private TextField filterText = new TextField();
    private Button cartButton = new Button("Cart");
    Button thumbsUpButton = new Button(new Icon(VaadinIcon.THUMBS_UP));

    public MainLayout() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("YourVoucherPL");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(thumbsUpButton, logo);

//        new Image("img/vaadin-logo.svg")
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");


        addToNavbar(header);

    }

}