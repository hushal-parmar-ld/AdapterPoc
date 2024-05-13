<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" indent="yes"/>

<!-- Identity template: copy all nodes and attributes -->
<xsl:template match="@* | node()">
    <xsl:copy>
        <xsl:apply-templates select="@* | node()"/>
    </xsl:copy>
</xsl:template>

<!-- Template to match <brand> and change it to <brand_name> -->
<xsl:template match="brand">
    <brand_name>
        <xsl:apply-templates select="@* | node()"/>
    </brand_name>
</xsl:template>
</xsl:stylesheet>

