import { Component, EventEmitter, Output } from '@angular/core';
import { ClickOutsideDirective } from '../../directives/click-outside.directive';

@Component({
    selector: 'app-header',
    imports: [ClickOutsideDirective],
    templateUrl: './header.component.html',
    styleUrl: './header.component.css'
})
export class HeaderComponent {
    @Output() toggle=new EventEmitter()
    @Output() openDialog=new EventEmitter()
    @Output() hideDialog=new EventEmitter()

    toggleMenu(){
        this.toggle.emit()
      }
    toggleDialog(){
        this.openDialog.emit()
    }
    hideIt(){
        this.hideDialog.emit()
    }


}
