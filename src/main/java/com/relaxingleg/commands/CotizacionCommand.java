package com.relaxingleg.commands;

import com.relaxingleg.commands.CotizacionService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.HashMap;
import java.util.Map;

public class CotizacionCommand extends ListenerAdapter {

    private static final Map<String, String> userEndpoints = new HashMap<>();
    private static final String COTIZACION_DOLAR = "cotizacion_dolar";

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("cotizacion")) {
            event.reply("Seleccione la moneda que desea consultar:").setEphemeral(true).queue();

            // Agregar botones para seleccionar la moneda
            event.getHook().editOriginal("Seleccione la moneda que desea consultar:")
                    .setActionRow(
                            Button.primary("moneda_dolar", "Dólar"),
                            Button.primary("moneda_euro", "Euro"),
                            Button.primary("moneda_real", "Real")
                    ).queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getButton().getId().startsWith("tipo_dolar_")) {
            handleDolarType(event);
            return; // Salimos de este método, ya que ya manejamos el tipo de Dólar.
        }

        switch (event.getButton().getId()) {
            case "moneda_dolar":
                askDolarType(event);
                break;
            case "moneda_euro":
                String euroEndpoint = "v1/cotizaciones/eur";
                processResponse(event, euroEndpoint);
                break;
            case "moneda_real":
                String realEndpoint = "v1/cotizaciones/brl";
                processResponse(event, realEndpoint);
                break;
            default:
                event.reply("Opción no válida.").setEphemeral(true).queue();
                break;
        }
    }

    private void askDolarType(ButtonInteractionEvent event) {
        // Preguntar el tipo de cotización del Dólar
        event.reply("Seleccione el tipo de cotización del Dólar:").setEphemeral(true).queue();

        event.getHook().editOriginal("Seleccione el tipo de cotización del Dólar:")
                .setActionRow(
                        Button.primary("tipo_dolar_blue", "Dólar Blue"),
                        Button.primary("tipo_dolar_oficial", "Dólar Oficial"),
                        Button.primary("tipo_dolar_bolsa", "Dólar Bolsa")
                ).queue();
    }

    private void handleDolarType(ButtonInteractionEvent event) {
        String endpoint = "";

        switch (event.getButton().getId()) {
            case "tipo_dolar_blue":
                endpoint = "v1/dolares/blue";
                break;
            case "tipo_dolar_oficial":
                endpoint = "v1/dolares/oficial";
                break;
            case "tipo_dolar_bolsa":
                endpoint = "v1/dolares/bolsa";
                break;
            default:
                event.reply("Opción no válida para el tipo de Dólar.").setEphemeral(true).queue();
                return;
        }

        processResponse(event, endpoint);
    }

    private void processResponse(ButtonInteractionEvent event, String endpoint) {
        try {
            String respuesta = CotizacionService.get(endpoint);
            event.reply("Respuesta para el endpoint seleccionado: " + respuesta).setEphemeral(true).queue();
        } catch (RuntimeException e) {
            event.reply("Error: " + e.getMessage()).setEphemeral(true).queue();
        }
    }
}
