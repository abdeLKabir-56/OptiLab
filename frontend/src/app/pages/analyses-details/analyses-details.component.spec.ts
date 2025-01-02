import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalysesDetailsComponent } from './analyses-details.component';

describe('AnalysesDetailsComponent', () => {
  let component: AnalysesDetailsComponent;
  let fixture: ComponentFixture<AnalysesDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnalysesDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnalysesDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
