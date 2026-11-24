
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification',
  imports: [FormsModule],
  standalone: true,
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
})
export class NotificationComponent {

  phoneNumber: string = '';
  message: string = '';
  responseMessage: string = '';

  constructor(private notificationService: NotificationService) { }

  sendNotification(): void {

    if (!this.phoneNumber.trim() || !this.message.trim()) {
      this.responseMessage = 'Please enter both phone number and message.';
      return;
    }

    this.notificationService.sendNotification(
      this.phoneNumber.trim(),
      this.message.trim()
    ).subscribe({

      next: (response) => {
        console.log('Success:', response);
        this.responseMessage = 'WhatsApp notification sent successfully!';
      },

      error: (error) => {
        console.error('Error:', error);
        this.responseMessage = 'Failed to send WhatsApp notification.';
      }

    });
  }
}
