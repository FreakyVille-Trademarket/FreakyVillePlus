package dk.fvtrademarket.fvplus.core.widgets;

import dk.fvtrademarket.fvplus.api.region.Region;
import dk.fvtrademarket.fvplus.api.service.region.RegionService;
import dk.fvtrademarket.fvplus.core.widgets.RegionListWidget.RegionListWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class RegionListWidget extends TextHudWidget<RegionListWidgetConfig> {

  private TextLine textLine;
  private RegionService regionService;

  public RegionListWidget(String id) {
    super(id);
  }

  @Override
  public void load(RegionListWidgetConfig config) {
    super.load(config);
    this.textLine = super.createLine("Callouts", "");
  }

  @Override
  public void onTick(boolean isEditorContext) {
    String content = "\n";
    if (this.getConfig().showDashes().get()) {
      content += " - ";
    }
  }

  public static class RegionListWidgetConfig extends TextHudWidgetConfig {

    @SliderSetting(min = 1, max = 5)
    private final ConfigProperty<Integer> maxRegions = new ConfigProperty<>(3);

    @SwitchSetting
    private final ConfigProperty<Boolean> showDashes = new ConfigProperty<>(true);

    public ConfigProperty<Integer> maxRegions() {
      return maxRegions;
    }

    public ConfigProperty<Boolean> showDashes() {
      return showDashes;
    }
  }
}
