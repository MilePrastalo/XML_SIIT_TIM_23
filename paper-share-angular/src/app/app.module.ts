import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { IndexComponent } from './index/index.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SearchDialogComponent } from './search-dialog/search-dialog.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { MaterialModule } from './material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreatePaperComponent } from './create-paper/create-paper.component';
import { ViewPaperComponent } from './view-paper/view-paper.component';
import { SafeHtmlPipe } from './safe-html.pipe';
import { AddReviewComponent } from './add-review/add-review.component';
import { ViewReviewComponent } from './view-review/view-review.component';
import { PaperListComponent } from './paper-list/paper-list.component';
import { AssignReviewComponent } from './assign-review/assign-review.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ReviewListComponent } from './review-list/review-list.component';
import { PaperReviewsComponent } from './paper-reviews/paper-reviews.component';
import { TokenInterceptorService } from './Service/token-interceptor.service';
import { UnitedReviewsComponent } from './united-reviews/united-reviews.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    IndexComponent,
    NavbarComponent,
    SearchDialogComponent,
    CreatePaperComponent,
    ViewPaperComponent,
    SafeHtmlPipe,
    AddReviewComponent,
    ViewReviewComponent,
    PaperListComponent,
    AssignReviewComponent,
    UserProfileComponent,
    ReviewListComponent,
    PaperReviewsComponent,
    UnitedReviewsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    HttpClientModule,

  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
