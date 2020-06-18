<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:sci="https://github.com/MilePrastalo/XML_SIIT_TIM_23"
	version="2.0">

	<xsl:template name="Chapter">
		<fo:block space-after="16px" space-before="16px">
			<fo:block font-size="18px" space-after="7px">
				<xsl:value-of select="./sci:ChapterName" />
			</fo:block>
			<xsl:for-each select="./sci:ChapterBody/sci:Chapter">
				<fo:block>
					<xsl:apply-templates />
				</fo:block>
			</xsl:for-each>
			<xsl:for-each
				select="./sci:ChapterBody/sci:ChapterContent">
				<fo:block>
					<fo:block font-size="12px" space-after="7px">
						<xsl:value-of select="." />
					</fo:block>
				</fo:block>
			</xsl:for-each>
		</fo:block>
	</xsl:template>


</xsl:stylesheet>
