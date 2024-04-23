import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

export abstract class GenericApi<Model> {
  
  private _apiUrl: string = 'http://localhost:8080';
  
  constructor(private _http: HttpClient) {  }

  getAll(controllerName: string, methodName: string, headers?: HttpHeaders): Observable<Model[]> {
    return this._http.get<Model[]>(`${this._apiUrl}/${controllerName}/${methodName}`, { headers: headers });
  }

  getById(controllerName: string, id: number, headers?: HttpHeaders): Observable<Model> {
    return this._http.get<Model>(`${this._apiUrl}/${controllerName}/${id}`, { headers: headers });
  }

  insert(controllerName: string, object: Model, headers?: HttpHeaders): Observable<Model[]> {
    return this._http.post<Model[]>(`${this._apiUrl}/${controllerName}/add`, { body: object, headers: headers });
  }

  update(controllerName: string, object: Model, headers?: HttpHeaders): Observable<Model[]> {
    return this._http.put<Model[]>(`${this._apiUrl}/${controllerName}/edit`, { body: object, headers: headers });
  }

  delete(controllerName: string, id: number, headers?: HttpHeaders): Observable<void> {
    return this._http.delete<void>(`${this._apiUrl}/${controllerName}/delete/${id}`, { headers: headers });
  }
}