import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LaboratoireComponent } from './laboratoire.component';
import { LaboratoireService } from '../services/laboratoire.service';
import { of } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('LaboratoireComponent', () => {
  let component: LaboratoireComponent;
  let fixture: ComponentFixture<LaboratoireComponent>;
  let mockLaboratoireService: Partial<LaboratoireService>;

  beforeEach(async () => {
    mockLaboratoireService = {
      getAllLaboratories: jest.fn().mockReturnValue(of([])),
      createLaboratory: jest.fn().mockReturnValue(of({})),
      updateLaboratory: jest.fn().mockReturnValue(of({})),
      deleteLaboratory: jest.fn().mockReturnValue(of(undefined))
    };

    await TestBed.configureTestingModule({
      declarations: [LaboratoireComponent],
      imports: [HttpClientTestingModule],
      providers: [
        { provide: LaboratoireService, useValue: mockLaboratoireService }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LaboratoireComponent);
    component = fixture.componentInstance;
  });

  it('should load all laboratories on init', () => {
    const dummyLaboratories = [
      { id: 1, nom: 'Lab 1', logo: 'logo1.png', nrc: 'NRC1', isActive: true, dateActivation: true },
      { id: 2, nom: 'Lab 2', logo: 'logo2.png', nrc: 'NRC2', isActive: false, dateActivation: false },
    ];
    
    (mockLaboratoireService.getAllLaboratories as jest.Mock).mockReturnValue(of(dummyLaboratories));
    
    fixture.detectChanges();
    
    expect(component.laboratoires.length).toBe(2);
    expect(component.laboratoires).toEqual(dummyLaboratories);
  });

  it('should add a new laboratory', () => {
    const newLaboratory = { 
      id: 3, 
      nom: 'Lab 3', 
      logo: 'logo3.png', 
      nrc: 'NRC3', 
      isActive: true, 
      dateActivation: true 
    };
    
    (mockLaboratoireService.createLaboratory as jest.Mock).mockReturnValue(of(newLaboratory));
    
    component.newLabo = newLaboratory;
    component.addLaboratory();
    
    expect(mockLaboratoireService.createLaboratory).toHaveBeenCalledWith(newLaboratory);
    expect(component.laboratoires.length).toBe(1);
    expect(component.laboratoires[0]).toEqual(newLaboratory);
  });

  it('should update an existing laboratory', () => {
    const updatedLaboratory = { 
      id: 1, 
      nom: 'Updated Lab', 
      logo: 'updated_logo.png', 
      nrc: 'NRC1', 
      isActive: true, 
      dateActivation: true 
    };
    
    (mockLaboratoireService.updateLaboratory as jest.Mock).mockReturnValue(of(updatedLaboratory));
    
    component.selectedLabo = updatedLaboratory;
    component.updateLaboratory();
    
    expect(mockLaboratoireService.updateLaboratory).toHaveBeenCalledWith(1, updatedLaboratory);
  });

  it('should delete a laboratory', () => {
    component.laboratoires = [
      { id: 1, nom: 'Lab 1', logo: 'logo1.png', nrc: 'NRC1', isActive: true, dateActivation: true },
    ];
    
    (mockLaboratoireService.deleteLaboratory as jest.Mock).mockReturnValue(of(undefined));
    
    component.deleteLaboratory(1);
    
    expect(mockLaboratoireService.deleteLaboratory).toHaveBeenCalledWith(1);
    expect(component.laboratoires.length).toBe(0);
  });
});