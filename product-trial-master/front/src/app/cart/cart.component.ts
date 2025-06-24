import { Component, inject } from "@angular/core";
import { CartService } from "./cart.service";
import { CommonModule } from "@angular/common";
import { ButtonModule } from "primeng/button";

@Component({
  selector: "app-cart",
  standalone: true,
  template: `
    <h2>Mon panier</h2>
    <div *ngIf="cart().length === 0">Le panier est vide.</div>
    <div *ngFor="let product of cart()">
      <div>{{ product.name }} - {{ product.price }} €</div>
      <p-button label="Supprimer" severity="danger" (onClick)="remove(product.id)" />
    </div>
  `,
  imports: [CommonModule, ButtonModule],
})
export class CartComponent {
  private readonly cartService = inject(CartService);
  readonly cart = this.cartService.cart;

  remove(id: number) {
    this.cartService.remove(id);
  }
}
