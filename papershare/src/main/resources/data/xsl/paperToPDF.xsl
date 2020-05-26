<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:sci="https://github.com/MilePrastalo/XML_SIIT_TIM_23"
	version="2.0">
	    <xsl:import href="templates.xsl"/>
	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master
					master-name="sciPubPage" page-height="29.7cm" page-width="21cm"
					margin-top="1cm" margin-bottom="2cm" margin-left="2.5cm"
					margin-right="2.5cm">
					<fo:region-body margin-top="2cm" margin-bottom="2cm" />
					<fo:region-before extent="2cm" />
					<fo:region-after extent="2cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="sciPubPage">
				<fo:flow flow-name="xsl-region-body" font-size="12px"
					font-family="Times" text-align="justify">
					<fo:block text-align="center" font-size="22px"
						space-after="16px">
						<xsl:value-of select="sci:ScientificPaper/sci:title" />
					</fo:block>

					<fo:block space-after="18px">
						<fo:table width="100%" table-layout="fixed"
							inline-progression-dimension="100%">
							<fo:table-body>
								<fo:table-row>
									<xsl:for-each
										select="sci:ScientificPaper/sci:Authors/sci:Author">
										<fo:table-cell text-align="center">
											<fo:block>
												<fo:block>
													<xsl:value-of select="sci:authorName" />
												</fo:block>
												<fo:block>
													<xsl:value-of select="sci:universityName" />
												</fo:block>
												<fo:block>
													<xsl:value-of select="sci:University/sci:city" />
													,
													<xsl:value-of
														select="sci:University/sci:country" />
												</fo:block>
											</fo:block>
										</fo:table-cell>
									</xsl:for-each>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>

					<fo:block space-after="14px" space-before="16px">
						<fo:block font-size="14px" space-after="5px"
							font-weight="bold">
							Abstract
						</fo:block>
						<xsl:for-each
							select="sci:ScientificPaper/sci:Abstract/sci:Paragraph">
							<xsl:value-of select="." />
						</xsl:for-each>
					</fo:block>


					<fo:block space-after="14px" space-before="16px">
						<fo:block font-size="14px" space-after="5px"
							font-weight="bold">
							Keywords
						</fo:block>
						<xsl:for-each
							select="sci:ScientificPaper/sci:Keywords/sci:Keyword">
							<xsl:value-of select="." />
							,
						</xsl:for-each>
					</fo:block>

					<xsl:for-each
						select="//sci:Chapter">
						<xsl:call-template name="Chapter" />
					</xsl:for-each>





				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

</xsl:stylesheet>