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
					<h1>
						<xsl:value-of select="/x:unitedReviews/x:paperName" />
					</h1>

					<xsl:for-each
						select="/x:unitedReviews/x:reviews/x:body">
						<h2>Review</h2>
						<h3>Criteria of evaluation: </h3>
						<table>
							<tr>
								<td>
									<b>Abstract:</b>
								</td>
								<td>
									<xsl:value-of
										select="x:criteriaEvaluation/x:abstract" />
								</td>
							</tr>
							<tr>
								<td>
									<b>Relevance:</b>
								</td>
								<td>
									<xsl:value-of
										select="x:criteriaEvaluation/x:relevance" />
								</td>
							</tr>

							<tr>
								<td>
									<b>Readability:</b>
								</td>
								<td>
									<xsl:value-of
										select="x:criteriaEvaluation/x:readability" />
								</td>
							</tr>
							<tr>
								<td>
									<b>Methodology:</b>
								</td>
								<td>
									<xsl:value-of
										select="x:criteriaEvaluation/x:methodology" />
								</td>
							</tr>
							<tr>
								<td>
									<b>Results</b>
								</td>
								<td>
									<xsl:value-of
										select="x:criteriaEvaluation/x:results" />
								</td>
							</tr>
						</table>
						<br />

						<h3>Overall Evaluation</h3>
						<p>
							<xsl:value-of select="x:overallEvaluation" />
						</p>

						<h3>Comments to Author</h3>
						<xsl:for-each
							select="x:chapterReviews/x:chapterReview">
							<p class="center">
								<b>Paragraph ID:</b>
								<xsl:value-of select="@partID" />
								<br />
								<b>Comment:</b>
								<xsl:value-of select="current()" />
							</p>
						</xsl:for-each>

						<h3>Comments to Editor</h3>
						<p>
							<xsl:value-of select="x:commentsToEditor" />
						</p>
					</xsl:for-each>



				</div>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>