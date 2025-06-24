import { Injectable, inject, signal } from "@angular/core";
import { Product } from "./product.model";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { catchError, map, Observable, of, tap } from "rxjs";

@Injectable({
    providedIn: "root"
}) export class ProductsService {

    private readonly http = inject(HttpClient);
    private readonly path = "http://127.0.0.1:8080/products";
    
    private readonly _products = signal<Product[]>([]);

    public readonly products = this._products.asReadonly();

    public get(): Observable<Product[]> {
        return this.http.get<Product[]>(this.path).pipe(
            catchError((error) => {
                return this.http.get<Product[]>("assets/products.json");
            }),
            tap((products) => this._products.set(products)),
        );
    }


public create(product: Product): Observable<boolean> {
    const { name, description, price, quantity } = product;
    const payload = { name, description, price, quantity };

    const headers = new HttpHeaders({
        email: 'admin@admin.com' // ← remplace par l'email réel si besoin
    });

    return this.http.post<Product>(this.path, payload, { headers }).pipe(
        tap((createdProduct) => {
            console.log("Produit ajouté :", createdProduct);
            this._products.update(products => [createdProduct, ...products]);
        }),
        map(() => true),
        catchError((error) => {
            console.error("Erreur lors de la création :", error);
            return of(false);
        })
    );
}


public update(product: Product): Observable<boolean> {
        return this.http.patch<boolean>(`${this.path}/${product.id}`, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => {
                return products.map(p => p.id === product.id ? product : p)
            })),
        );
    }

    public delete(productId: number): Observable<boolean> {
        return this.http.delete<boolean>(`${this.path}/${productId}`).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => products.filter(product => product.id !== productId))),
        );
    }
}