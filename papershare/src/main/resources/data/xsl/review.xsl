<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:sci="https://XML_SIIT_TIM_23/Review"
    version="2.0">
    
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    h1 { text-align:center }
                </style>
            </head>
            
            <body>
                <h1>Reviewer</h1>
                    <p>
                        <xsl:value-of select="/review/metadata/reviewer"/>,
                        <xsl:value-of select="/review/metadata/publicationName"/>,
                        <xsl:value-of select="/review/metadata/submissionDate"/>,
                    </p>
                
                <h2>Criterial of evaluation: </h2>
                <table>
                	<tr>
                		<td>
                			Relevance of research problem
                		</td>
                		<td>
                			<xsl:value-of select="/review/body/criteriaEvaluation/relevanceOfResearchProblem"/>
                		</td>
                	</tr>
                	<tr>
                		<td>
                			Introduction
                		</td>
                		<td>
                			<xsl:value-of select="/review/body/criteriaEvaluation/introduction"/>
                		</td>
                	</tr>
                	<tr>
                		<td>
                			Conceptual Quality
                		</td>
                		<td>
                			<xsl:value-of select="/review/body/criteriaEvaluation/conceptualQuality"/>
                		</td>
                	</tr>
                	<tr>
                		<td>
                			Methodological Quality
                		</td>
                		<td>
                			<xsl:value-of select="/review/body/criteriaEvaluation/methodologicalQuality"/>
                		</td>
                	</tr>
                	<tr>
                		<td>
                			Results
                		</td>
                		<td>
                			<xsl:value-of select="/review/body/criteriaEvaluation/results"/>
                		</td>
                	</tr>
                	<tr>
                		<td>
                			Discussion
                		</td>
                		<td>
                			<xsl:value-of select="/review/body/criteriaEvaluation/discussion"/>
                		</td>
                	</tr>
                	<tr>
                		<td>
                			Readability
                		</td>
                		<td>
                			<xsl:value-of select="/review/body/criteriaEvaluation/readability"/>
                		</td>
                	</tr>
                </table>
                
                <h2>Overall Evaluation</h2>
                <p>
                	<xsl:value-of select="/review/body/overallEvaluation"/>
                </p>
                
                <h2>Comments to Author</h2>
                <xsl:for-each select="/review/body/commentsToAuthor/proposedChange">
	         		<p class = "center">
	 					Paragraph ID: <xsl:value-of select="@partID"/>
	 					Comment: <xsl:value-of select="current()"/>
	         		</p>
	         	</xsl:for-each>
	         	
	         	<h2>Comments to Editor</h2>
	         	<p>
                	<xsl:value-of select="/review/body/commentsToEditor"/>
                </p>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>