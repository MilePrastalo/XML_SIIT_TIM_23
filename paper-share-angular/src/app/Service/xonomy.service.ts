import { Injectable } from '@angular/core';
declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  TCriteriaGrade = ["excellent", "very good", "good", "bad", "insufficient"]
  TOverallGrade = ["acceptable without changes", "needs minor changes", "needs major changess", "reject"]

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
          caption: 'Add <sci:title>',
          action: Xonomy.newElementChild,
          actionParameter: '<sci:title xmlns:sci="https://github.com/MilePrastalo/XML_SIIT_TIM_23"></sci:title>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("sci:title");
          }
        }, {
          caption: 'Add <Authors>',
          action: Xonomy.newElementChild,
          actionParameter: '<Authors></Authors>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("Authors");
          }
        }, {
          caption: 'Add <sci:Abstract>',
          action: Xonomy.newElementChild,
          actionParameter: '<sci:Abstract xmlns:sci="https://github.com/MilePrastalo/XML_SIIT_TIM_23"></sci:Abstract>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("sci:Abstract");
          }
        }, {
          caption: 'Add <Keywords>',
          action: Xonomy.newElementChild,
          actionParameter: '<sci:Keywords xmlns:sci="https://github.com/MilePrastalo/XML_SIIT_TIM_23"></sci:Keywords>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("sci:Keywords");
          }
        },
        {
          caption: 'Add <sci:Chapters>',
          action: Xonomy.newElementChild,
          actionParameter: '<sci:Chapters xmlns:sci="https://github.com/MilePrastalo/XML_SIIT_TIM_23"></sci:Chapters>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("sci:Chapters");
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
      "sci:title": {
        mustBeBefore: ['Authors', 'Abstract', 'Keywords', 'sci:Chapters', 'References'],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }],
      },
      Authors: {
        mustBeBefore: ['Abstract', 'Keywords', 'sci:Chapters', 'References'],
        menu: [{
          caption: 'Add <sci:Author>',
          action: Xonomy.newElementChild,
          actionParameter: '<sci:Author  xmlns:sci="https://github.com/MilePrastalo/XML_SIIT_TIM_23"></sci:Author>'
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      "sci:Keywords": {
        mustBeBefore: ['sci:Chapters', 'References'],
        menu: [{
          caption: 'Add <Keyword>',
          action: Xonomy.newElementChild,
          actionParameter: '<Keyword></Keyword>'
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      "sci:Chapters": {
        mustBeBefore: ['References'],
        menu: [{
          caption: 'Add <sci:Chapter>',
          action: Xonomy.newElementChild,
          actionParameter: '<sci:Chapter xmlns:sci="https://github.com/MilePrastalo/XML_SIIT_TIM_23"></sci:Chapter>'
        }]
      },
      "sci:Author": {
        menu: [{
          caption: 'Add <authorUsername>',
          action: Xonomy.newElementChild,
          actionParameter: '<authorUsername></authorUsername>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("authorUsername");
          }
        }, {
          caption: 'Add <authorName>',
          action: Xonomy.newElementChild,
          actionParameter: '<authorName></authorName>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("authorName");
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
        mustBeBefore: ['authorName'],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }],
      },
      authorName: {
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
      "sci:Abstract": {
        mustBeBefore: ['Keywords', 'sci:Chapters', 'References'],
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
      "sci:Chapter": {
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
          caption: 'Add <ChapterContent>',
          action: Xonomy.newElementChild,
          actionParameter: '<ChapterContent></ChapterContent>'
        }, {
          caption: 'Add <Chapter>',
          action: Xonomy.newElementChild,
          actionParameter: '<Chapter></Chapter>'
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      ChapterContent: {
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


  public reviewSpecification = {
    elements: {
      review: {
        menu: [{
          caption: 'Add <metadata>',
          action: Xonomy.newElementChild,
          actionParameter: '<metadata></metadata>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('metadata');
          }
        }, {
          caption: 'Add <body>',
          action: Xonomy.newElementChild,
          actionParameter: '<body></body>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('body');
          }
        }]
      },
      metadata: {
        mustBeBefore: ['body'],
        menu: [{
          mustBeBefore: ['submissionDate'],
          caption: 'Add <paperName>',
          action: Xonomy.newElementChild,
          actionParameter: '<paperName></paperName>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('paperName');
          }
        }, {
          caption: 'Add <submissionDate>',
          action: Xonomy.newElementChild,
          actionParameter: '<submissionDate></submissionDate>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('submissionDate');
          }
        }]
      },
      body: {
        menu: [{
          caption: 'Add <criteriaEvaluation>',
          action: Xonomy.newElementChild,
          actionParameter: '<criteriaEvaluation></criteriaEvaluation>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('criteriaEvaluation');
          }
        },
        {
          caption: 'Add <overallEvaluation>',
          action: Xonomy.newElementChild,
          actionParameter: '<overallEvaluation> </overallEvaluation>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('overallEvaluation');
          }
        },
        {
          caption: 'Add <chapterReviews>',
          action: Xonomy.newElementChild,
          actionParameter: '<chapterReviews></chapterReviews>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('chapterReviews');
          }
        },
        {
          caption: 'Add <commentsToEditor>',
          action: Xonomy.newElementChild,
          actionParameter: '<commentsToEditor></commentsToEditor>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('commentsToEditor');
          }
        }]
      },
      criteriaEvaluation: {
        mustBeBefore: ['overallEvaluation'],
        menu: [{
          caption: 'Add <abstract>',
          action: Xonomy.newElementChild,
          actionParameter: '<abstract> </abstract>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('abstract');
          }
        },
        {
          caption: 'Add <relevance>',
          action: Xonomy.newElementChild,
          actionParameter: '<relevance> </relevance>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('relevance');
          }
        },
        {
          caption: 'Add <readability>',
          action: Xonomy.newElementChild,
          actionParameter: '<readability> </readability>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('readability');
          }
        },
        {
          caption: 'Add <methodology>',
          action: Xonomy.newElementChild,
          actionParameter: '<methodology> </methodology>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('methodology');
          }
        },
        {
          caption: 'Add <results>',
          action: Xonomy.newElementChild,
          actionParameter: '<results> </results>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('results');
          }
        }]
      },
      abstract: {
        mustBeBefore: ['relevance'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TCriteriaGrade
      },
      relevance: {
        mustBeBefore: ['readability'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TCriteriaGrade
      },
      readability: {
        mustBeBefore: ['methodology'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TCriteriaGrade
      },
      methodology: {
        mustBeBefore: ['results'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TCriteriaGrade
      },
      results: {
        mustBeBefore: ['methodology'],
        asker: Xonomy.askPicklist,
        oneliner: true,
        askerParameter: this.TCriteriaGrade
      },
      overallEvaluation: {
        mustBeBefore: ['chapterReviews'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TOverallGrade
      },
      chapterReviews: {
        mustBeBefore: ['commentsToEditor'],
        menu: [{
          caption: 'Add <chapterReview>',
          action: Xonomy.newElementChild,
          actionParameter: '<chapterReview></chapterReview>'
        }]
      },
      commentsToEditor: {
        asker: Xonomy.askString,
        hasText: true,
      },
      chapterReview: {
        asker: Xonomy.askString,
        hasText: true,
        menu: [{
          caption: 'Add @chapterID',
          action: Xonomy.newAttribute,
          actionParameter: { name: 'chapterID', value: '' },
          hideIf(jsElement) {
            return jsElement.hasAttribute('chapterID');
          }
        }]
      }
    }
  };
}
