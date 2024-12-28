import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardDashComponent } from './card-dash.component';

describe('CardDashComponent', () => {
  let component: CardDashComponent;
  let fixture: ComponentFixture<CardDashComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardDashComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardDashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
