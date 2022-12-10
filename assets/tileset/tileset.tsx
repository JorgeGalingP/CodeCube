<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.9" tiledversion="1.9.2" name="tileset" tilewidth="64" tileheight="64" spacing="2" margin="2" tilecount="30" columns="6" objectalignment="center">
 <image source="../tileset.png" width="398" height="332"/>
 <tile id="5">
  <properties>
   <property name="type" value="floor"/>
  </properties>
 </tile>
 <tile id="6">
  <properties>
   <property name="type" value="floor"/>
  </properties>
 </tile>
 <tile id="7">
  <properties>
   <property name="type" value="floor"/>
  </properties>
 </tile>
 <tile id="11">
  <properties>
   <property name="type" value="wall"/>
  </properties>
 </tile>
 <tile id="12">
  <properties>
   <property name="variable" value="right"/>
   <property name="type" value="box"/>
  </properties>
 </tile>
 <tile id="20">
    <properties>
    <property name="color" value="green"/>
     <property name="type" value="target"/>
    </properties>
   </tile>
  <tile id="21">
   <properties>
    <property name="variable" value="up"/>
    <property name="type" value="box"/>
   </properties>
  </tile>
  <tile id="22">
    <properties>
     <property name="variable" value="negation"/>
     <property name="type" value="box"/>
    </properties>
   </tile>
   <tile id="23">
     <properties>
      <property name="type" value="player"/>
     </properties>
    </tile>
  <tile id="24">
   <properties>
    <property name="variable" value="left"/>
    <property name="type" value="box"/>
   </properties>
  </tile>
  <tile id="25">
     <properties>
      <property name="variable" value="function"/>
      <property name="type" value="box"/>
     </properties>
    </tile>
 </tileset>
