import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder }  from '@angular/forms';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService } from '../services/alert.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
templateUrl: 'test.component.html',
 styleUrls: ['test.component.css']
})
export class TestComponent implements OnInit {
    loginForm: FormGroup;
    loading = false;
    loadingPrevious = true;
    loadingNext = false;
    showError = false;
    submitted = false;
    returnUrl: string;
    answers : any;
    question:string;
    selanswer:string;
    keys:string[];
    quesCount:number;
    now: Date = new Date();
    starttime:number = this.now.getTime();
    endtime:number;
    
    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService
    ) {
    	this.answers = new Map();
    	
    	setInterval(() => {
          this.now = new Date();
        }, 1);
        
       this.loading = true;
    	let selectedEntry;
        this.authenticationService.getFirstQuestion()
            .pipe(first())
            .subscribe(
                data => {
                console.log(this.question);
                console.log(this.answers);
                this.answers = data.answerMap;
                this.keys = Object.keys(this.answers);
                this.question = data.question;
                this.authenticationService.currentUserValue.maxQuestions = data.id;
                this.authenticationService.currentUserValue.questionId = data.questionId;
    			this.authenticationService.currentUserValue.maxQuestions = data.maxQuestions;
                this.quesCount = data.questionId;
                    //this.router.navigate(['/test']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
    ngOnInit() {
    };
    
    next() {
    	this.showError = false;
    	console.log(this.selanswer);
    	console.log(this.authenticationService.currentUserValue.questionId);
    	console.log(this.authenticationService.currentUserValue.maxQuestions);
    	
    	if (this.selanswer === null || this.selanswer === 'null' || this.selanswer === undefined || this.selanswer === 'undefined') {
    		this.showError = true;
    		return;
    	}
    	this.loadingPrevious = false;
    	this.loadingNext = false;
    	
    	 this.loading = true;
    	let selectedEntry;
    	if (this.authenticationService.currentUserValue.questionId != this.authenticationService.currentUserValue.maxQuestions) { 
        this.authenticationService.getNextQuestion(this.selanswer)
            .pipe(first())
            .subscribe(
                data => {
                console.log(this.question);
                console.log(this.answers);
                this.answers = data.answerMap;
                this.keys = Object.keys(this.answers);
                this.question = data.question;
                this.authenticationService.currentUserValue.questionId = data.questionId;
                 this.selanswer = JSON.stringify(data.answerSelected);
                 this.quesCount = data.questionId;
                 console.log('this.selanswer ::: ' +this.selanswer);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
            } else {
            	this.authenticationService.currentUserValue.answerId = this.selanswer;
            	console.log('before result : ' +this.authenticationService.currentUserValue.answerId);
            	this.endtime = new Date().getTime();
            	this.authenticationService.currentUserValue.duration = (this.endtime - this.starttime)/1000;
            	console.log(this.endtime);
            	console.log(this.starttime);
  				console.log(this.authenticationService.currentUserValue.duration);
            	this.router.navigate(['/result']);
            }  
                
    	if (this.authenticationService.currentUserValue.questionId + 1 == this.authenticationService.currentUserValue.maxQuestions) { 
    		document.getElementById('next_button').innerHTML = 'Submit';
        }
    };
    previous() {
    	this.showError = false;
    	console.log(this.selanswer);
    	console.log(this.authenticationService.currentUserValue.questionId);
    	console.log(this.authenticationService.currentUserValue.maxQuestions);
    	if (this.selanswer === null || this.selanswer === 'null' || this.selanswer === undefined || this.selanswer === 'undefined') { 
    		this.showError = true;
    		return;
    	}
    	 this.loadingNext = false;
    	 this.loadingPrevious = false
    	 
    	  this.loading = true;
    	let selectedEntry;
        this.authenticationService.getPreviousQuestion(this.selanswer)
            .pipe(first())
            .subscribe(
                data => {
                console.log(this.question);
                console.log(this.answers);
                this.answers = data.answerMap;
                this.keys = Object.keys(this.answers);
                this.question = data.question;
                this.authenticationService.currentUserValue.questionId = data.questionId;
                 this.selanswer = JSON.stringify(data.answerSelected);
                 this.quesCount = data.questionId;
                 document.getElementById('next_button').innerHTML = 'Next';
                 console.log(this.answers);
                 console.log('this.selanswer ::: ' +this.selanswer);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
                
    	if (this.authenticationService.currentUserValue.questionId == 1) {
             this.loadingPrevious = true;
        }
    };
    
    }
