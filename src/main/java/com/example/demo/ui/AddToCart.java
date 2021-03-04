package com.example.demo.ui;

import com.example.demo.backend.entity.Product;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class AddToCart extends FormLayout {
    TextField productName = new TextField("Product Name");
    TextField productPrice = new TextField("Product Price");
    TextField productImage = new TextField("Product Image");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Product> binder = new BeanValidationBinder<>(Product.class);
    private Product product;

    public AddToCart(List<Product> all) {
        addClassName("product-form");
        binder.bindInstanceFields(this);


        add(productName,
            productPrice,
            productImage,
            createButtonsLayout());
    }

    public void setContact(Product product) {
        this.product = product;
        binder.readBean(product);
    }
    
    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, product)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(product);
            fireEvent(new SaveEvent(this, product));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


    public static abstract class AddToCartEvent extends ComponentEvent<AddToCart> {
        private Product product;

        protected AddToCartEvent(AddToCart source, Product contact) {
            super(source, false);
            this.product = contact;
        }

        public Product getContact() {
            return product;
        }
    }

    public static class SaveEvent extends AddToCartEvent {
        SaveEvent(AddToCart source, Product product) {
            super(source, product);
        }
    }

    public static class DeleteEvent extends AddToCartEvent {
        DeleteEvent(AddToCart source, Product product) {
            super(source, product);
        }

    }

    public static class CloseEvent extends AddToCartEvent {
        CloseEvent(AddToCart source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
