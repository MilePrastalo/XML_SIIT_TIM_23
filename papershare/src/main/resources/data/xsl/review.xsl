<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:x="https://github.com/MilePrastalo/XML_SIIT_TIM_23"
	xmlns:rv="https://github.com/MilePrastalo/XML_SIIT_TIM_23"
	version="2.0">

	<xsl:template match="/">
		<html>
			<head>
				<style>
					.center {
					width: 500px;
					margin: 0 auto;
					padding: 5px;
					font-size:
					16px;
					}

					.tab {
					margin-left: 2.5em
					}
				</style>
			</head>

			<body>
				<div class="center">
					<h1>Review</h1>
					<p>
						<b>Scientific Paper:	</b>
						<xsl:value-of
							select="/x:review/x:metadata/x:paperName" />
						<br />

						<b>Reviewer:</b>
						<xsl:value-of
							select="/x:review/x:metadata/x:reviewer" />
						<br />

						<b>Status:  </b>
						<xsl:value-of select="/x:review/x:metadata/x:status" />
						<br />

						<b>Created on:  </b>
						<xsl:value-of
							select="/x:review/x:metadata/x:submissionDate" />
						<br />
					</p>

					<h2>Criteria of evaluation: </h2>
					<table>
						<tr>
							<td>
								<b>Abstract:</b>
							</td>
							<td>
								<xsl:value-of
									select="/x:review/x:body/x:criteriaEvaluation/x:abstract" />
							</td>
						</tr>
						<tr>
							<td>
								<b>Relevance:</b>
							</td>
							<td>
								<xsl:value-of
									select="/x:review/x:body/x:criteriaEvaluation/x:relevance" />
							</td>
						</tr>

						<tr>
							<td>
								<b>Readability:</b>
							</td>
							<td>
								<xsl:value-of
									select="/x:review/x:body/x:criteriaEvaluation/x:readability" />
							</td>
						</tr>
						<tr>
							<td>
								<b>Methodology:</b>
							</td>
							<td>
								<xsl:value-of
									select="/x:review/x:body/x:criteriaEvaluation/x:methodology" />
							</td>
						</tr>
						<tr>
							<td>
								<b>Results</b>
							</td>
							<td>
								<xsl:value-of
									select="/x:review/x:body/x:criteriaEvaluation/x:results" />
							</td>
						</tr>
					</table>
					<br />

					<h2>Overall Evaluation</h2>
					<p>
						<xsl:value-of
							select="/x:review/x:body/x:overallEvaluation" />
					</p>

					<h2>Comments to Author</h2>
					<xsl:for-each
						select="/x:review/x:body/x:chapterReviews/x:chapterReview">
						<p class="center">
							<b>Paragraph ID:</b>
							<xsl:value-of select="@partID" />
							<br />
							<b>Comment:</b>
							<xsl:value-of select="current()" />
						</p>
					</xsl:for-each>

					<h2>Comments to Editor</h2>
					<p>
						<xsl:value-of
							select="/x:review/x:body/x:commentsToEditor" />
					</p>
				</div>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>