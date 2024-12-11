import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BootstComponent } from './bootst.component';

describe('BootstComponent', () => {
  let component: BootstComponent;
  let fixture: ComponentFixture<BootstComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BootstComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BootstComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
