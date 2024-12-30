import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileAccordingComponent } from './profile-according.component';

describe('ProfileAccordingComponent', () => {
  let component: ProfileAccordingComponent;
  let fixture: ComponentFixture<ProfileAccordingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileAccordingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileAccordingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
