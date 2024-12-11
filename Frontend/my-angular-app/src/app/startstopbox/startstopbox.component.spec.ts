import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StartstopboxComponent } from './startstopbox.component';

describe('StartstopboxComponent', () => {
  let component: StartstopboxComponent;
  let fixture: ComponentFixture<StartstopboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StartstopboxComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StartstopboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
