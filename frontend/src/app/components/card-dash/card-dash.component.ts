import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card-dash',
  imports: [],
  templateUrl: './card-dash.component.html',
  styleUrl: './card-dash.component.less'
})
export class CardDashComponent {
  @Input() icon!:string
  @Input() title!:string
  @Input() number!:number
  @Input() color!:string
  @Input() textColor!:string

}
