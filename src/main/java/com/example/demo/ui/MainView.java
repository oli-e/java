package com.example.demo.ui;

import com.example.demo.backend.entity.Cart;
import com.example.demo.backend.entity.Product;
import com.example.demo.backend.service.ProductService;
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

@Route(value="", layout = MainLayout.class)
public class MainView extends VerticalLayout {

    private ProductService productService;
    private Grid<Product> grid = new Grid<>(Product.class);
    private Grid<Cart> cartContent = new Grid<>(Cart.class);
    private TextField filterText = new TextField();
    Label kupa = new Label("Tu bedzie div koszyczka");

    private Div cart = new Div(kupa, cartContent);
    private Button cartButton = new Button("Cart", evt -> {cart.setVisible(true); });


    public MainView(ProductService productService) {
        this.productService = productService;
        cartButton.addClassName("cart-button");
        addClassName("list-view");
        setSizeFull();
        getToolbar();
        configureGrid();




        add(getToolbar(), configureCart(), configureContent());
        updateList();
    }

    public Div configureContent(){
        Div content = new Div(grid);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }


    // Change the Div to PopupContent czy jakoś tak
    public Div configureCart(){
        cart.addClassName("cart");
        cart.setVisible(false);

        Button close = new Button("Close", evt -> {cart.setVisible(false); });
        close.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addClickShortcut(Key.ESCAPE);

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