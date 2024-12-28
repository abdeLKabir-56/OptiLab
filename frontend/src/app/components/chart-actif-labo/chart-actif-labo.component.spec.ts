import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartActifLaboComponent } from './chart-actif-labo.component';

describe('ChartActifLaboComponent', () => {
  let component: ChartActifLaboComponent;
  let fixture: ComponentFixture<ChartActifLaboComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChartActifLaboComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChartActifLaboComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
