﻿using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Web;

namespace GuessWhoOnePiece.Components.Elements.TextEntry;

public partial class TextEntry : ComponentBase
{
    [Parameter] public List<string> Items { get; set; } = []; 
    
    [Parameter] public bool WithAutoComplete { get; set; }
    [Parameter] public EventCallback<string> OnItemSelected { get; set; } // Event when an item is selected
    
    [Parameter] public EventCallback<KeyboardEventArgs> OnKeyPressed { get; set; }
    
    [Parameter] public EventCallback<ChangeEventArgs> OnInputKeys { get; set; }

    private List<string> _filteredItems = [];
    private string SearchText { get; set; }

    // Méthode appelée lors de la modification de l'entrée de texte
    private async Task OnInput(ChangeEventArgs e)
    {
        await OnInputKeys.InvokeAsync(e);
        SearchText = e.Value.ToString();
        FilterItems();
    }

    // Méthode appelée lors de la pression d'une touche (pour la touche "Enter")
    private async Task OnKeyDown(KeyboardEventArgs e)
    {
        await OnKeyPressed.InvokeAsync(e);
        if (e.Key != "Enter") return;
        
        foreach (var item in Items.Where(item => item == SearchText))
        {
            _ = SelectItem(item);
        }
    }

    // Méthode pour sélectionner un item
    private async Task SelectItem(string selectedItem)
    {
        await OnItemSelected.InvokeAsync(selectedItem);
        _filteredItems.Clear();
        SearchText = string.Empty;
    }

    // Méthode pour filtrer les items en fonction de la saisie de l'utilisateur
    private void FilterItems()
    {
        _filteredItems.Clear();
        if (string.IsNullOrEmpty(SearchText)) return;
            
        foreach (var item in Items.Where(item => item.Contains(SearchText, StringComparison.InvariantCultureIgnoreCase)))
        {
            _filteredItems.Add(item);
        }
    }
}