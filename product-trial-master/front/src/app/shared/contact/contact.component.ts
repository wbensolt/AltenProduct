import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { InputTextModule } from "primeng/inputtext";
import { InputTextareaModule } from "primeng/inputtextarea";
import { ButtonModule } from "primeng/button";

@Component({
  selector: "app-contact",
  standalone: true,
  template: `
    <h2>Contactez-nous</h2>
    <form #form="ngForm" (ngSubmit)="onSubmit()" class="p-fluid">
      <div class="field">
        <label for="email">Email</label>
        <input id="email" name="email" type="email" pInputText [(ngModel)]="email" required />
      </div>
      <div class="field">
        <label for="message">Message</label>
        <textarea id="message" name="message" pInputTextarea [(ngModel)]="message" required maxlength="300"></textarea>
      </div>
      <p-button type="submit" label="Envoyer" [disabled]="!form.valid" />
    </form>

    <p *ngIf="sent" style="color: green;">Demande de contact envoyée avec succès.</p>
  `,
  imports: [CommonModule, FormsModule, InputTextModule, InputTextareaModule, ButtonModule],
})
export class ContactComponent {
  email = "";
  message = "";
  sent = false;

  onSubmit() {
    this.sent = true;
  }
}
