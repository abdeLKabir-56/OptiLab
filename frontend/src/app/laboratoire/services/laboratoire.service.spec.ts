import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LaboratoireService } from './laboratoire.service';
import 'jasmine';
describe('LaboratoireService', () => {
  let service: LaboratoireService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [LaboratoireService]
    });

    service = TestBed.inject(LaboratoireService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should fetch all laboratories', () => {
    const dummyLaboratories = [
      { id: 1, nom: 'Lab 1', logo: 'logo1.png', nrc: 'NRC1', isActive: true, dateActivation: true },
      { id: 2, nom: 'Lab 2', logo: 'logo2.png', nrc: 'NRC2', isActive: false, dateActivation: false },
    ];

    service.getAllLaboratories().subscribe(laboratories => {
      expect(laboratories.length).toBe(2);
      expect(laboratories).toEqual(dummyLaboratories);
    });

    const req = httpMock.expectOne('http://localhost:8050/api/v1/laboratory');
    expect(req.request.method).toBe('GET');
    req.flush(dummyLaboratories);
  });

  it('should fetch a laboratory by ID', () => {
    const dummyLaboratory = { id: 1, nom: 'Lab 1', logo: 'logo1.png', nrc: 'NRC1', isActive: true, dateActivation: true };

    service.getLaboratoryById(1).subscribe(laboratory => {
      expect(laboratory).toEqual(dummyLaboratory);
    });

    const req = httpMock.expectOne('http://localhost:8050/api/v1/laboratory/1');
    expect(req.request.method).toBe('GET');
    req.flush(dummyLaboratory);
  });

  it('should create a new laboratory', () => {
    const newLaboratory = { id: 3, nom: 'Lab 3', logo: 'logo3.png', nrc: 'NRC3', isActive: true, dateActivation: true };

    service.createLaboratory(newLaboratory).subscribe(laboratory => {
      expect(laboratory).toEqual(newLaboratory);
    });

    const req = httpMock.expectOne('http://localhost:8050/api/v1/laboratory');
    expect(req.request.method).toBe('POST');
    req.flush(newLaboratory);
  });

  it('should update a laboratory', () => {
    const updatedLaboratory = { id: 1, nom: 'Updated Lab', logo: 'updated_logo.png', nrc: 'NRC1', isActive: true, dateActivation: true };

    service.updateLaboratory(1, updatedLaboratory).subscribe(laboratory => {
      expect(laboratory).toEqual(updatedLaboratory);
    });

    const req = httpMock.expectOne('http://localhost:8050/api/v1/laboratory/1');
    expect(req.request.method).toBe('PUT');
    req.flush(updatedLaboratory);
  });

  it('should delete a laboratory', () => {
    service.deleteLaboratory(1).subscribe();

    const req = httpMock.expectOne('http://localhost:8050/api/v1/laboratory/1');
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });
});
