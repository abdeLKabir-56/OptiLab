import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LaboratoireService } from './laboratoire.service';

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
      { id: 1, nom: 'Lab 1', logo: 'logo1.png', nrc: 'NRC1', isActive: true, dateActivation: new Date() }
    ];

    service.getAllLaboratories().subscribe(labs => {
      expect(labs).toEqual(dummyLaboratories);
    });

    const req = httpMock.expectOne('http://localhost:8050/api/v1/laboratory/all');
    expect(req.request.method).toBe('GET');
    req.flush(dummyLaboratories);
  });

  it('should create a new laboratory', () => {
    const newLaboratory = {
      id: 1,
      nom: 'Lab 1',
      logo: 'logo1.png',
      nrc: 'NRC1',
      isActive: true,
      dateActivation: new Date()
    };

    service.createLaboratory(newLaboratory).subscribe(lab => {
      expect(lab).toEqual(newLaboratory);
    });

    const req = httpMock.expectOne('http://localhost:8050/api/v1/laboratory/add');
    expect(req.request.method).toBe('POST');
    req.flush(newLaboratory);
  });

  it('should update a laboratory', () => {
    const updatedLaboratory = {
      id: 1,
      nom: 'Updated Lab',
      logo: 'updated_logo.png',
      nrc: 'NRC1',
      isActive: true,
      dateActivation: new Date()
    };

    service.updateLaboratory(1, updatedLaboratory).subscribe(lab => {
      expect(lab).toEqual(updatedLaboratory);
    });

    const req = httpMock.expectOne('http://localhost:8050/api/v1/laboratory/update/1');
    expect(req.request.method).toBe('PUT');
    req.flush(updatedLaboratory);
  });

  it('should delete a laboratory', () => {
    service.deleteLaboratory(1).subscribe();

    const req = httpMock.expectOne('http://localhost:8050/api/v1/laboratory/delete/1');
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });
});