import {inject, TestBed, getTestBed, async, fakeAsync, ComponentFixture} from '@angular/core/testing';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, RouterModule,  Routes } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder }  from '@angular/forms';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import { first } from 'rxjs/operators';
import { HttpClientModule } from '@angular/common/http'; 
import { AuthenticationService } from '../services/authentication.service';
import { NgModule } from '@angular/core';
import { LoginComponent } from './login.component';
import { TestComponent } from '../test/test.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let spy: any;
  let authenticationService:  AuthenticationService;

	const fakeActivatedRoute = {
       snapshot: { data: {} }
   }
    
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports : [
        ReactiveFormsModule,
        RouterModule.forRoot([]),
        HttpClientModule,
        FormsModule
      ],
      declarations: [ LoginComponent, TestComponent ], 
      providers: [
       { provide: ActivatedRoute, useValue: fakeActivatedRoute }, 
       { provide: 'authenticationService', useClass: AuthenticationService }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should populate form fields', async(() => {
    component.loginForm.controls['username'].setValue('tom');
    component.loginForm.controls['password'].setValue('123');
     expect(component.loginForm.controls['username'].value).toBe('tom');
    
  }));
  
   
  
  

  
  
  

});