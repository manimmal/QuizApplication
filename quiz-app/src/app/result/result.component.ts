import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder }  from '@angular/forms';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService } from '../services/alert.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
templateUrl: 'result.component.html',
 styleUrls: ['result.component.css']
})
export class ResultComponent implements OnInit {
    loginForm: FormGroup;
    loading = false;
    totalcount:number;
     correctcount:number;
     incorrectcount:number;
      answers : any;
      duration:number;
    
    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService
    ) {
    	this.answers = new Map();
    	
       this.loading = true;
    	let selectedEntry;
        this.authenticationService.getFinalResult()
            .pipe(first())
            .subscribe(
                data => {
                console.log(data.correctAnswers);
                this.correctcount = data.correctAnswers;
                this.totalcount = this.authenticationService.currentUserValue.maxQuestions;
                this.incorrectcount = this.totalcount - this.correctcount;
                this.duration = this.authenticationService.currentUserValue.duration;
                
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
    ngOnInit() {
    };
    
   
    }
