import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { LaboratoireComponent } from './laboratoire.component';
import { LaboratoireService } from '../services/laboratoire.service';
import { of } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing'; 
interface Laboratoire {
  id: number;
  nom: string;
  logo: string;
  nrc: string;
  isActive: boolean;
  dateActivation: Date;
}

describe('LaboratoireComponent', () => {
  let component: LaboratoireComponent;
  let fixture: ComponentFixture<LaboratoireComponent>;
  let laboratoireService: any;

  beforeEach(async () => {
    const spy = {
      getAllLaboratories: jest.fn().mockReturnValue(of([])),
      getLaboratoryById: jest.fn().mockReturnValue(of(null)),
      createLaboratory: jest.fn().mockReturnValue(of(null)),
      updateLaboratory: jest.fn().mockReturnValue(of(null)),
      deleteLaboratory: jest.fn().mockReturnValue(of(void 0))
    };

    await TestBed.configureTestingModule({
      imports: [LaboratoireComponent, HttpClientTestingModule,RouterTestingModule],
      providers: [
        { provide: LaboratoireService, useValue: spy }
      ]
    }).compileComponents();

    laboratoireService = TestBed.inject(LaboratoireService);
  });

  beforeEach(() => {
    jest.spyOn(window, 'confirm').mockReturnValue(true);

    fixture = TestBed.createComponent(LaboratoireComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load all laboratories on init', () => {
    const dummyLaboratories: Laboratoire[] = [
      { id: 1, nom: 'Lab 1', logo: 'logo1.png', nrc: 'NRC1', isActive: true, dateActivation: new Date() },
      { id: 2, nom: 'Lab 2', logo: 'logo2.png', nrc: 'NRC2', isActive: false, dateActivation: new Date() }
    ];

    laboratoireService.getAllLaboratories.mockReturnValue(of(dummyLaboratories));
    
    component.ngOnInit(); // Ensure ngOnInit is called

    fixture.detectChanges();

    expect(component.laboratoires.length).toBe(2);
    expect(component.laboratoires).toEqual(dummyLaboratories);
  });

  it('should add a new laboratory', waitForAsync(() => {
    const newLaboratory: Laboratoire = {
      id: 3,
      nom: 'Lab 3',
      logo: 'logo3.png',
      nrc: 'NRC3',
      isActive: true,
      dateActivation: new Date()
    };
  
    laboratoireService.createLaboratory.mockReturnValue(of(newLaboratory));
    component.newLabo = newLaboratory;
  
    component.addLaboratory();
    fixture.detectChanges();
  
    fixture.whenStable().then(() => {
      expect(laboratoireService.createLaboratory).toHaveBeenCalledWith(newLaboratory);
    });
  }));
  it('should delete a laboratory', () => {
    component.laboratoires = [{
      id: 1,
      nom: 'Lab 1',
      logo: 'logo1.png',
      nrc: 'NRC1',
      isActive: true,
      dateActivation: new Date()
    }];

    laboratoireService.deleteLaboratory.mockReturnValue(of(void 0));

    component.deleteLaboratory(1);
    fixture.detectChanges();

    expect(laboratoireService.deleteLaboratory).toHaveBeenCalledWith(1);
    expect(component.laboratoires.length).toBe(0);
  });
});