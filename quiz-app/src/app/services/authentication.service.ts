import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {Http, Headers} from '@angular/http';

import { User } from '../models/user';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;
    baseurl:string = 'http://localhost:8080/quiz/v1';
    authId:string = 'admin';
    authPasswd:string = 'test';

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(username: string, password: string) {
   
    let body = {
    	"requestId" : "4545",
    	"username" : username,
    	"password" : password
    };
	    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.authId + ':' + this.authPasswd) });

        return this.http.post<any>(this.baseurl+`/user/login`, body, {headers} )
            .pipe(map(user => {
                // login successful if there's a jwt token in the response
                if (user) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.currentUserSubject.next(user);
                }

                return user;
            }));
    }
    
     getFirstQuestion() {
	    let body = {
	    	"requestId" : "4545",
	    	"userId" : this.currentUserValue.id,
	    	"questionId" : this.currentUserValue.questionId
	    };
		    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.authId + ':' + this.authPasswd) });
	
	        return this.http.post<any>(this.baseurl+`/user/getFirstQuestion`, body, {headers})
	            .pipe(map(user => {
	                return user;
	            }));
    }
    
    getNextQuestion(answerId:string) { 
	    let body = {
	    	"requestId" : "4545",
	    	"userId" : this.currentUserValue.id,
	    	"questionId" : this.currentUserValue.questionId, 
	    	"answerId" : answerId
	    };
		   const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.authId + ':' + this.authPasswd) });
	
	        return this.http.post<any>(this.baseurl+`/user/getNextQuestion`, body, {headers})
	            .pipe(map(user => {
	                return user;
	            }));
    }
    
    getPreviousQuestion(answerId:string) {
	    let body = {
	    	"requestId" : "4545",
	    	"userId" : this.currentUserValue.id,
	    	"questionId" : this.currentUserValue.questionId,
	    	"answerId" : answerId
	    };
		     const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.authId + ':' + this.authPasswd) });
	
	        return this.http.post<any>(this.baseurl+`/user/getPreviousQuestion`, body, {headers})
	            .pipe(map(user => {
	                return user;
	            }));
    }
    
    getFinalResult() {
	    let body = {
	    	"requestId" : "4545",
	    	"userId" : this.currentUserValue.id,
	    	"questionId" : this.currentUserValue.questionId,
	    	"answerId" : this.currentUserValue.answerId
	    };
		     const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.authId + ':' + this.authPasswd) });
	
	        return this.http.post<any>(this.baseurl+`/user/getFinalResult`, body, {headers})
	            .pipe(map(user => {
	                return user;
	            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}