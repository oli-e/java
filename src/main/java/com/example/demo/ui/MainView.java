package com.example.demo.ui;

import com.example.demo.backend.entity.Cart;
import com.example.demo.backend.entity.Product;
import com.example.demo.backend.service.CartService;
import com.example.demo.backend.service.ProductService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route(value="", layout = MainLayout.class)
public class MainView extends VerticalLayout {

    private ProductService productService;
    private CartService cartService;
    private Grid<Product> grid = new Grid<>(Product.class);
    private Grid<Cart> cartContent = new Grid<>(Cart.class);
    private TextField filterText = new TextField();
    private Div cart = new Div(cartContent);
    private Button cartButton = new Button("Cart", evt -> {cart.setVisible(true); });

    public MainView(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
        cartButton.addClassName("cart-button");
        addClassName("list-view");
        setSizeFull();
        getToolbar();



        add(getToolbar(), configureCart(), showProducts());
        updateList();
    }

    public Div configureContent(){
        configureGrid();
        Div content = new Div(grid);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }


    public HorizontalLayout showProducts(){
        List<String> p = productService.showNames();

        HorizontalLayout products = new HorizontalLayout();
        products.setSizeFull();

        for (String news : p) {
            Div productsContainer = new Div();
            Label l = new Label(news);

            String[] names = news.split(",");

            Button addToCartButton = new Button("Add To Cart", evt -> addToCart() );
            productsContainer.addClassName("productsContainer");
            productsContainer.add(new Paragraph(names[0]), new Hr(), new Paragraph(names[1]), new Hr(), new Paragraph(names[2]), addToCartButton);
            products.add(productsContainer);
        }
        products.setAlignItems(Alignment.CENTER);
//        products.setDefaultVerticalComponentAlignment(
//                FlexComponent.Alignment.CENTER);

        return products;

    }


    public Div configureCart(){
        cart.addClassName("cart");
        cart.setSizeFull();
        cart.setVisible(false);
        cartContent.setItems(cartService.findAll());
        cartContent.setColumns("product", "amount");
        Button close = new Button("Close", evt -> {cart.setVisible(false); });
        close.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addClickShortcut(Key.ESCAPE);
        close.getStyle().set("margin-right", "auto");

        cart.add(close);
        return cart;
    }

    public HorizontalLayout getToolbar() {
        filterText.setPlaceholder("search");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        filterText.addValueChangeListener(e -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, cartButton);
        toolbar.addClassName("toolbar");

        toolbar.setAlignItems(Alignment.CENTER);
        toolbar.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);


        return toolbar;
    }


    private void configureGrid() {
        grid.addClassName("product-grid");
        grid.setSizeFull();
        grid.setColumns("productName", "productPrice", "productDescription", "productImage");
    }

    private void updateList() {
        grid.setItems(productService.findAll(filterText.getValue()));
    }

    private void addToCart(){

    }
}