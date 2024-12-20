import { Component } from '@angular/core';
import {ChangeDetectionStrategy} from '@angular/core';
import {TuiButton} from '@taiga-ui/core';
 



@Component({
    selector: 'app-sider',
    imports: [TuiButton],
    templateUrl: './sider.component.html',
    styleUrl: './sider.component.css', 
    changeDetection: ChangeDetectionStrategy.OnPush,


})
export class SiderComponent {
  
}
