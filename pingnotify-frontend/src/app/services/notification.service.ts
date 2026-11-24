
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private apiUrl = 'http://localhost:8080/api/notifications/send';

  constructor(private http: HttpClient) { }

  sendNotification(
    recipientPhoneNumber: string,
    message: string
  ): Observable<string> {

    const requestBody = {
      recipientPhoneNumber: recipientPhoneNumber,
      message: message
    };

    return this.http.post(
      this.apiUrl,
      requestBody,
      { responseType: 'text' }
    );
  }
}
