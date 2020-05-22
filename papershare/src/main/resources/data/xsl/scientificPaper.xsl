<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	 xmlns:sci="https://XML_SIIT_TIM_23/ScientificPaper"
	 version="2.0">
    
    	<xsl:template match="/">
	      <html>
	      <head>
			   <style>
				h1 { text-align:center }
				.authors {text-align:center }
			   </style>
		  </head>
	      
	      <body>
	      <h1>Scientific Paper</h1>
	      

	         	<xsl:for-each select="/ScientificPaper/sci:Authors/sci:Author">
	         		<p class = "authors">
	         			<xsl:value-of select="sci:authorUsername"></xsl:value-of>,
	         			<xsl:value-of select="sci:University/sci:universityName"/>,
	         			<xsl:value-of select="sci:University/sci:city"/>,
	         			<xsl:value-of select="sci:University/sci:country"/>,
	         		</p>
	         	</xsl:for-each>
	         	
				<h1>Abstract</h1>
	         	
			
	         	<xsl:for-each select="/ScientificPaper/sci:Abstract/sci:Paragraph">
	         		<p><xsl:value-of select="."/></p><br/><br/>
	         	</xsl:for-each>
				
				
				<span>Keywords:</span>
				<p>
	         	<xsl:for-each select="/ScientificPaper/sci:Keywords/sci:Keyword">
	         		<xsl:value-of select="."/>
	         	</xsl:for-each>
				</p>
				
	         	<div>
	         	<xsl:for-each select="/ScientificPaper/sci:Chapters/sci:Chapter">
	         		
	         			
	         				<h1><xsl:value-of select="sci:ChapterName"/></h1>
	         		
	         				<xsl:for-each select="/ScientificPaper/sci:Chapters/sci:Chapter/sci:ChapterBody/sci:ChapterContent">
									<p>
									<xsl:value-of select="sci:MixedContent/sci:chapterText"/>
									<xsl:value-of select="sci:MixedContent/sci:chapterImage"/>
									</p>
									<p>
	         						<xsl:value-of select="sci:chapterText"/>
	         						</p>
	         				</xsl:for-each>
	         				
	         	</xsl:for-each>
				</div>
				
				<h1>References</h1>
	       
	         		<xsl:for-each select="/ScientificPaper/sci:References/sci:Reference">
	         		<p>
	         			<xsl:value-of select="sci:AuthorInformation"/>,
	         			<xsl:value-of select="sci:year"/>,
	         			<xsl:value-of select="sci:magazine"/>,
	         			<xsl:value-of select="sci:article"/>,
	         			<xsl:value-of select="sci:publisher"/>
	         		</p>
	         		</xsl:for-each>
	         	
	       
	      </body></html>
	   </xsl:template>
   
</xsl:stylesheet>