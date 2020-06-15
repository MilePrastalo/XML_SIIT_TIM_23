import { Injectable } from '@angular/core';
declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  TCriteriaGrade = ["very good", "good", "small weaknesses", "great weaknesses", "absolutely insufficient"]
  TOverallGrade = ["acceptable without changes", "acceptable with minor revisions", "acceptable with major revisions", "encouraged to resubmit", "reject"]

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
          actionParameter: '<sci:title xmlns:sci="https://XML_SIIT_TIM_23/ScientificPaper"></sci:title>',
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
          actionParameter: '<sci:Abstract xmlns:sci="https://XML_SIIT_TIM_23/ScientificPaper"></sci:Abstract>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("sci:Abstract");
          }
        }, {
          caption: 'Add <Keywords>',
          action: Xonomy.newElementChild,
          actionParameter: '<sci:Keywords xmlns:sci="https://XML_SIIT_TIM_23/ScientificPaper"></sci:Keywords>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("sci:Keywords");
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
      "sci:title": {
        mustBeBefore: ['Authors', 'Abstract', 'Keywords', 'Chapters', 'References'],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }],
      },
      Authors: {
        mustBeBefore: ['Abstract', 'Keywords', 'Chapters', 'References'],
        menu: [{
          caption: 'Add <sci:Author>',
          action: Xonomy.newElementChild,
          actionParameter: '<sci:Author  xmlns:sci="https://XML_SIIT_TIM_23/ScientificPaper"></sci:Author>'
        }, {
          caption: 'Delete element',
          action: Xonomy.deleteElement
        }]
      },
      "sci:Keywords": {
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
          caption: 'Add <sci:Chapter>',
          action: Xonomy.newElementChild,
          actionParameter: '<sci:Chapter xmlns:sci="https://XML_SIIT_TIM_23/ScientificPaper"></sci:Chapter>'
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
      "sci:Abstract": {
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
          caption: 'Add <publicationName>',
          action: Xonomy.newElementChild,
          actionParameter: '<publicationName></publicationName>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('publicationName');
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
          caption: 'Add <commentsToAuthor>',
          action: Xonomy.newElementChild,
          actionParameter: '<commentsToAuthor></commentsToAuthor>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('commentsToAuthor');
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
          caption: 'Add <relevanceOfResearchProblem>',
          action: Xonomy.newElementChild,
          actionParameter: '<relevanceOfResearchProblem> </relevanceOfResearchProblem>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('relevanceOfResearchProblem');
          }
        },
        {
          caption: 'Add <introduction>',
          action: Xonomy.newElementChild,
          actionParameter: '<introduction> </introduction>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('introduction');
          }
        },
        {
          caption: 'Add <conceptualQuality>',
          action: Xonomy.newElementChild,
          actionParameter: '<conceptualQuality> </conceptualQuality>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('conceptualQuality');
          }
        },
        {
          caption: 'Add <results>',
          action: Xonomy.newElementChild,
          actionParameter: '<results> </results>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('results');
          }
        },
        {
          caption: 'Add <discussion>',
          action: Xonomy.newElementChild,
          actionParameter: '<discussion> </discussion>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('discussion');
          }
        },
        {
          caption: 'Add <readability>',
          action: Xonomy.newElementChild,
          actionParameter: '<readability> </readability>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('readability');
          }
        }]
      },
      relevanceOfResearchProblem: {
        mustBeBefore: ['introduction'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TCriteriaGrade
      },
      introduction: {
        mustBeBefore: ['conceptualQuality'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TCriteriaGrade
      },
      conceptualQuality: {
        mustBeBefore: ['methodologicalQuality'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TCriteriaGrade
      },
      methodologicalQuality: {
        mustBeBefore: ['results'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TCriteriaGrade
      },
      results: {
        mustBeBefore: ['discussion'],
        asker: Xonomy.askPicklist,
        oneliner: true,
        askerParameter: this.TCriteriaGrade
      },
      discussion: {
        mustBeBefore: ['readability'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TCriteriaGrade
      },
      readability: {
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TCriteriaGrade
      },
      overallEvaluation: {
        mustBeBefore: ['commentsToAuthor'],
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: this.TOverallGrade
      },
      commentsToAuthor: {
        mustBeBefore: ['commentsToEditor'],
        menu: [{
          caption: 'Add <proposedChange>',
          action: Xonomy.newElementChild,
          actionParameter: '<proposedChange></proposedChange>'
        }]
      },
      commentsToEditor:{
        asker: Xonomy.askString,
        hasText: true,
      },
      proposedChange: {
        asker: Xonomy.askString,
        hasText: true,
        menu: [{
          caption: 'Add @partID',
          action: Xonomy.newAttribute,
          actionParameter: { name: 'partID', value: '' },
          hideIf(jsElement) {
            return jsElement.hasAttribute('partID');
          }
        }]
      }
    }
  };
}
