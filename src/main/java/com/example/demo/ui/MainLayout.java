package com.example.demo.ui;

import com.example.demo.backend.entity.Product;
import com.example.demo.backend.service.ProductService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    private TextField filterText = new TextField();
    private Button cartButton = new Button("Cart");

    public MainLayout() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("YourVoucherPL");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(logo);

//        new Image("img/vaadin-logo.svg")
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");


        addToNavbar(header);

    }

}