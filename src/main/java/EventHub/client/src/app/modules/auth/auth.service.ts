import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  isLoggedIn() {
    const token = localStorage.getItem('token'); // get token from local storage
    
    if (token) {
      const payload = atob(token.split('.')[1]); // decode payload of token
      console.log(payload)
      const parsedPayload = JSON.parse(payload); // convert payload into an Object
      console.log(parsedPayload)
      
      return parsedPayload.exp > Date.now() / 1000; // check if token is expired
    }
    
    return false;
  }
}