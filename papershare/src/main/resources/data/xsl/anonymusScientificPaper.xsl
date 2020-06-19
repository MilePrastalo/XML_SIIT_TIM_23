<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:x="https://github.com/MilePrastalo/XML_SIIT_TIM_23"
	xmlns:sci="https://github.com/MilePrastalo/XML_SIIT_TIM_23"
	version="2.0">
	<xsl:import href="htmlTemplates.xsl" />

	<xsl:template match="/">
		<html>
			<head>
				<style>
					body {text-align: justify; text-justify: inter-word; margin: auto 50px
					auto 50px;}
					.center {text-align: center;)
				</style>
			</head>

			<body>
				<h1 class="center">
					<xsl:value-of select="/x:ScientificPaper/sci:title"></xsl:value-of>
				</h1>

				<h1>Abstract</h1>


				<xsl:for-each
					select="/x:ScientificPaper/sci:Abstract/sci:Paragraph">
					<p>
						<xsl:value-of select="." />
					</p>
					<br />
					<br />
				</xsl:for-each>


				<p>
					<b>Keywords:</b>
					<xsl:for-each
						select="/x:ScientificPaper/sci:Keywords/sci:Keyword">
						<xsl:value-of select="." />
						,
					</xsl:for-each>
				</p>

				<div>
					<xsl:for-each select="//sci:Chapter">
						<b>Chapter ID:</b>
							<xsl:value-of select="@id" />
						<xsl:call-template name="Chapter" />
					</xsl:for-each>

				</div>

				<h1>References</h1>

				<xsl:for-each
					select="/x:ScientificPaper/sci:References/sci:Reference">
					<p>
						<xsl:value-of select="sci:AuthorInformation" />
						,
						<xsl:value-of select="sci:year" />
						,
						<xsl:value-of select="sci:magazine" />
						,
						<xsl:variable name="paperTitle" select="sci:article" />
						<a href="http://localhost:4200/view-paper/{$paperTitle}">
							<xsl:value-of select="sci:article" />
						</a>
						,
						<xsl:value-of select="sci:publisher" />
					</p>
				</xsl:for-each>


			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>