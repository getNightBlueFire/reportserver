package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.dto;

import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportConfigDto;

public class DatasinkManagerImportConfigDto extends TreeImportConfigDto<AbstractDatasinkManagerNodeDto> {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   private boolean generateRandomKey;

   public boolean isGenerateRandomKey() {
      return generateRandomKey;
   }

   public void setGenerateRandomKey(boolean generateRandomKey) {
      this.generateRandomKey = generateRandomKey;
   }

}