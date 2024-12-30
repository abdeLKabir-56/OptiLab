import { Directive, ElementRef, EventEmitter, HostListener, Output } from '@angular/core';

@Directive({
  selector: '[appClickOutside]', // Ensure this matches the selector used in the template
})
export class ClickOutsideDirective {
  @Output() clickOutside = new EventEmitter<void>();

  constructor(private elementRef: ElementRef) {}

  @HostListener('document:click', ['$event.target'])
  onClick(targetElement: HTMLElement): void {
    // Check if the clicked target is outside the element to which the directive is applied
    const clickedInside = this.elementRef.nativeElement.contains(targetElement);
    if (!clickedInside) {
      // Emit the event when the click is outside the element
      this.clickOutside.emit();
    }
  }
}
