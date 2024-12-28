import { NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';
import {ChangeDetectionStrategy} from '@angular/core';
import { RouterModule } from '@angular/router';
 



@Component({
    selector: 'app-sider',
    imports: [NgIf , RouterModule],
    templateUrl: './sider.component.html',
    styleUrl: './sider.component.css', 
    changeDetection: ChangeDetectionStrategy.OnPush,


})
export class SiderComponent {
    @Input() collapsedStatus!:boolean
  
}
