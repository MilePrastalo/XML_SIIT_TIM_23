import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CreatePaperComponent } from './create-paper/create-paper.component';
import { ViewPaperComponent } from './view-paper/view-paper.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { ViewReviewComponent } from './view-review/view-review.component';
import { PaperListComponent } from './paper-list/paper-list.component';
import { AssignReviewComponent } from './assign-review/assign-review.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { PaperReviewsComponent } from './paper-reviews/paper-reviews.component';
import { UnitedReviewsComponent } from './united-reviews/united-reviews.component';
import { ViewAnonPaperComponent } from './view-anon-paper/view-anon-paper.component';


const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'add', component: CreatePaperComponent },
  { path: 'add/:title', component: CreatePaperComponent },
  { path: 'addReview/:title', component: AddReviewComponent },
  { path: 'view-paper/:name', component: ViewPaperComponent },
  { path: 'review/:name', component: ViewReviewComponent },
  { path: 'review/sciPaper/:name', component: ViewAnonPaperComponent },
  { path: 'united-reviews/:name', component: UnitedReviewsComponent },
  { path: 'assign-review/:title', component: AssignReviewComponent },
  { path: 'paper-list', component: PaperListComponent },
  { path: 'user-profile', component: UserProfileComponent },
  { path: 'paper-reviews/:paperName', component: PaperReviewsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

