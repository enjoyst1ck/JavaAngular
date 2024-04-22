import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

export abstract class GenericApi<Model> {
  
  private _apiUrl: string = 'http://localhost:8080';
  
  constructor(private _http: HttpClient) {  }

  getAll(controllerName: string, methodName: string): Observable<Model[]> {
    return this._http.get<Model[]>(`${this._apiUrl}/${controllerName}/${methodName}`);
  }

  getById(controllerName: string, id: number): Observable<Model> {
    return this._http.get<Model>(`${this._apiUrl}/${controllerName}/${id}`);
  }

  insert(controllerName: string, object: Model): Observable<Model> {
    return this._http.post<Model>(`${this._apiUrl}/${controllerName}/add`, object);
  }

  update(controllerName: string, object: Model): Observable<Model> {
    return this._http.put<Model>(`${this._apiUrl}/${controllerName}/edit`, object);
  }

  delete(controllerName: string, id: number): Observable<void> {
    return this._http.delete<void>(`${this._apiUrl}/${controllerName}/delete/${id}`);
  }
}