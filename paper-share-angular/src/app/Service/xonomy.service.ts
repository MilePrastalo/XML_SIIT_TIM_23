import { Injectable } from '@angular/core';
declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  public scientificPublicationSpecification = {
    elements: {
      ScientificPaper: {
        menu: [{
          caption: "Add @language=\"english\"",
          action: Xonomy.newAttribute,
          actionParameter: { name: "language", value: "english" },
          hideIf: function (jsElement) {
            return jsElement.hasAttribute("language");
          }
        }, {
          caption: 'Add <Authors>',
          action: Xonomy.newElementChild,
          actionParameter: '<Authors></Authors>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("Authors");
          }
        }, {
          caption: 'Add <Abstract>',
          action: Xonomy.newElementChild,
          actionParameter: '<Abstract></Abstract>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("Abstract");
          }
        }, {
          caption: 'Add <Keywords>',
          action: Xonomy.newElementChild,
          actionParameter: '<Keywords></Keywords>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("Keywords");
          }
        },
        {
          caption: 'Add <Chapters>',
          action: Xonomy.newElementChild,
          actionParameter: '<Chapters></Chapters>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("Chapters");
          }
        },
        {
          caption: 'Add <References>',
          action: Xonomy.newElementChild,
          actionParameter: '<References></References>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("References");
          }
        }
        ],
          attributes: {
          "language": {
            asker: Xonomy.askString,
            menu: [{
              caption: 'Delete this @language',
              action: Xonomy.deleteAttribute
            }]
          }
        }
      },
      Authors: {
        mustBeBefore: ['Abstract', 'Keywords', 'Chapters', 'References'],
        menu: [{
          caption: 'Add <Author>',
          action: Xonomy.newElementChild,
          actionParameter: '<Author></Author>'
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      Keywords: {
        mustBeBefore: ['Chapters', 'References'],
        menu: [{
          caption: 'Add <Keyword>',
          action: Xonomy.newElementChild,
          actionParameter: '<Keyword></Keyword>'
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      Chapters: {
        mustBeBefore: ['References'],
        menu: [{
          caption: 'Add <Chapter>',
          action: Xonomy.newElementChild,
          actionParameter: '<Chapter></Chapter>'
        }]
      },
      Author: {
        menu: [{
          caption: 'Add <authorUsername>',
          action: Xonomy.newElementChild,
          actionParameter: '<authorUsername></authorUsername>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("authorUsername");
          }
        }, {
          caption: 'Add <University>',
          action: Xonomy.newElementChild,
          actionParameter: '<University></University>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("University");
          }
        }],
        attributes: {
        }
      },
      authorUsername: {
        mustBeBefore: ['University'],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }],
      },
      University: {
        menu: [{
          caption: 'Add <universityName>',
          action: Xonomy.newElementChild,
          actionParameter: '<universityName></universityName>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("universityName");
          }
        }, {
          caption: 'Add <city>',
          action: Xonomy.newElementChild,
          actionParameter: '<city></city>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("city");
          }
        }, {
          caption: 'Add <country>',
          action: Xonomy.newElementChild,
          actionParameter: '<country></country>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("country");
          }
        }
        ]
      },
      Abstract: {
        mustBeBefore: ['Keywords', 'Chapters', 'References'],
        menu: [{
          caption: 'Add <Paragraph>',
          action: Xonomy.newElementChild,
          actionParameter: '<Paragraph></Paragraph>'
        }
        ]
      },
      Paragraph: {
        hasText: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }],
      },
      universityName: {
        mustBeBefore: ['ity', 'country'],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }],
      },
      city: {
        mustBeBefore: ['country'],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      country: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      Keyword: {
        asker: Xonomy.askString,
        hasText: true,
        oneliner: true,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }],
        attributes: {
        }
      },
      Chapter: {
        menu: [{
          caption: "Add attribute 'id'",
          action: Xonomy.newAttribute,
          actionParameter: { name: "id", value: "0" },
          hideIf(jsElement) {
            return jsElement.hasAttribute('id');
          }
        }, {
          caption: 'Add <ChapterName>',
          action: Xonomy.newElementChild,
          actionParameter: '<ChapterName></ChapterName>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("ChapterName");
          }
        }, {
          caption: 'Add <ChapterBody>',
          action: Xonomy.newElementChild,
          actionParameter: '<ChapterBody></ChapterBody>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("ChapterBody");
          }
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }],
        attributes: {
          id: {
            asker: Xonomy.askString,
            menu: [{
              caption: 'Delete this @id',
              action: Xonomy.deleteAttribute
            }]
          }
        }
      },
      ChapterName: {
        mustBeBefore: ['ChapterBody'],
        asker: Xonomy.askString,
        hasText: true,
        oneliner: true,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      ChapterBody: {
        menu: [{
          caption: 'Add <MixedContent>',
          action: Xonomy.newElementChild,
          actionParameter: '<MixedContent></MixedContent>'
        }, {
          caption: 'Add <Chapter>',
          action: Xonomy.newElementChild,
          actionParameter: '<Chapter></Chapter>'
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      MixedContent: {
        menu: [{
          caption: 'Add <chapterText>',
          action: Xonomy.newElementChild,
          actionParameter: '<chapterText></chapterText>'
        }, {
          caption: 'Add <chapterImage>',
          action: Xonomy.newElementChild,
          actionParameter: '<chapterImage></chapterImage>'
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      chapterText: {
        asker: Xonomy.askString,
        hasText: true,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      chapterImage: {
        asker: Xonomy.askString,
        hasText: true,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      References: {
        menu: [{
          caption: 'Add <Reference>',
          action: Xonomy.newElementChild,
          actionParameter: '<Reference></Reference>'
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      Reference: {
        menu: [{
          caption: 'Add <AuthorInformation>',
          action: Xonomy.newElementChild,
          actionParameter: '<AuthorInformation></AuthorInformation>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("AuthorInformation");
          }
        }, {
          caption: 'Add <year>',
          action: Xonomy.newElementChild,
          actionParameter: '<year></year>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("year");
          }
        }, {
          caption: 'Add <magazine>',
          action: Xonomy.newElementChild,
          actionParameter: '<magazine></magazine>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("magazine");
          }
        }, {
          caption: 'Add <article>',
          action: Xonomy.newElementChild,
          actionParameter: '<article></article>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("article");
          }
        }, {
          caption: 'Add <publisher>',
          action: Xonomy.newElementChild,
          actionParameter: '<publisher></publisher>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("publisher");
          }
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }],
      },
      AuthorInformation: {
        mustBeBefore: ['year', 'magazine', 'article', 'publisher'],
        asker: Xonomy.askString,
        hasText: true,
        oneliner: true,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      year: {
        mustBeBefore: ['magazine', 'article', 'publisher'],
        asker: Xonomy.askString,
        hasText: true,
        oneliner: true,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      magazine: {
        mustBeBefore: ['article', 'publisher'],
        asker: Xonomy.askString,
        hasText: true,
        oneliner: true,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      article: {
        mustBeBefore: ['publisher'],
        asker: Xonomy.askString,
        hasText: true,
        oneliner: true,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      publisher: {
        asker: Xonomy.askString,
        hasText: true,
        oneliner: true,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      }
    }
  };
}
