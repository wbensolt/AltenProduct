// cart.service.ts
import { Injectable, signal } from "@angular/core";
import { Product } from "app/products/data-access/product.model";

@Injectable({ providedIn: "root" })
export class CartService {
  private readonly _cart = signal<Product[]>([]);
  readonly cart = this._cart.asReadonly();

  add(product: Product) {
    this._cart.update((current) => [...current, product]);
  }

  remove(productId: number) {
    this._cart.update((current) => current.filter(p => p.id !== productId));
  }

  clear() {
    this._cart.set([]);
  }

  get totalQuantity() {
    return this._cart().length;
  }
}
