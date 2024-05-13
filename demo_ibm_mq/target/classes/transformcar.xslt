<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!-- Identity template: copy all nodes and attributes to the output -->
    <xsl:template match="@* | node()">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()"/>
        </xsl:copy>
    </xsl:template>

    <!-- Template to match 'car' elements and transform them -->
    <xsl:template match="car">
        <vehicle>
            <xsl:apply-templates select="id"/>
            <xsl:apply-templates select="brand"/>
            <xsl:apply-templates select="model"/>
            <xsl:apply-templates select="year"/>
        </vehicle>
    </xsl:template>

</xsl:stylesheet>
