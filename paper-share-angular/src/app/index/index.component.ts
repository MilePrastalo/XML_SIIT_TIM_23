import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  public loggedIn: boolean;

  constructor() { }
  papers:any =  [
    { title: "Etika u razvoju vestacke inteligencije", date: "11.6.2019.", authors: "MIle Prastalo, Jelena Cuk, Nikola Saric", keywords: "veštačka inteligencija, etika, bezbednost, privatnost, nezaposlenost",  description: "Glavni zadatak ovog rada je da upozna osobe koje ranije nisu imale kontakt sa veštačkom inteligencijom sa problemima koje ona donosi i koje bi mogla doneti."},
    { title: "Pregled problema koji jos uvek ne mogu da se rese dubokim ucenjem", date: "11.6.2019.", authors: "Uros Ogrizovic, Marko Simanic",  keywords: "duboko učcenje (Deep Learning, DL), konvolucione neuronske mrežze (Convolutional Neural Networks, CNN), mašsinsko učcenje (Machine Learning, ML)",  description: "Poslednjih godina, duboko uˇcenje (Deep Learning, DL) je postiglo veliki uspeh u mnogim oblastima. Medutim, postoji mnogo oblasti u kojima joˇs uvek ne daje zadovoljavaju´ce rezultate."}
]
  ngOnInit(): void {
    this.loggedIn = false;
    if ( localStorage.getItem('role') !== '' ) {
      this.loggedIn = true;
    }
  }

}
