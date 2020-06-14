<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sci="https://github.com/MilePrastalo/XML_SIIT_TIM_23"
	version="2.0">

	<xsl:template name="Chapter">
		<div>
			<h2>
				<xsl:value-of select="./sci:ChapterName" />
			</h2>
			<p>
				<xsl:for-each select="./sci:ChapterBody/sci:Chapter">
					<xsl:apply-templates />
				</xsl:for-each>
				<xsl:for-each
					select="./sci:ChapterBody/sci:ChapterContent">
					<xsl:value-of select="." />
				</xsl:for-each>
			</p>
		</div>
	</xsl:template>


</xsl:stylesheet>